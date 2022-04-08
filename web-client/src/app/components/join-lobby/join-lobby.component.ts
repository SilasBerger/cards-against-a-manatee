import {Component, OnInit} from '@angular/core';
import {WebsocketConnectionService} from '../../services/websocket-connection.service';
import ServerService from '../../services/server.service';
import Lobby from '../../model/lobby';
import BackendEvent from '../../model/backend-event';
import {SharedStateService} from '../../services/shared-state.service';
import User from '../../model/user';
import {LobbyService} from '../../services/lobby.service';
import {first} from 'rxjs/operators';
import {UserService} from '../../services/user.service';
import {ApplicationRoute, ApplicationRouter} from '../../routing/routing';
import Issue from '../../services/issue';

@Component({
  selector: 'app-join-lobby',
  templateUrl: './join-lobby.component.html',
  styleUrls: ['./join-lobby.component.scss']
})
export class JoinLobbyComponent implements OnInit {

  lobbyId: string;

  constructor(private _websocketConnectionService: WebsocketConnectionService,
              private _serverService: ServerService,
              private _router: ApplicationRouter,
              private _sharedStateService: SharedStateService,
              private _lobbyService: LobbyService,
              userService: UserService) {
    this._lobbyService.reset();
    if (!this._websocketConnectionService.isConnected()) {
      _router.navigateOnIssue(Issue.WebSocketDisconnected);
    } else if (!userService.userId) {
      _router.navigateOnIssue(Issue.UserNotSet);
    }
  }

  ngOnInit(): void {
  }

  onJoin() {
    if (!this.lobbyId || this.lobbyId.length < 1) {
      console.error('Invalid lobby id.');
      return;
    }
    this._websocketConnectionService.onEventSubject
      .pipe(first())
      .subscribe((event) => this.onEvent(event));
    this._websocketConnectionService.send('request.request_join_lobby', { lobbyId: this.lobbyId });
  }

  onEvent(event: BackendEvent) {
    const expectedEvent = 'notification.notify_current_lobby_state';
    if (event.headers.eventName !== expectedEvent) {
      this._sharedStateService.error = `Expected ${expectedEvent}, received ${event.headers.eventName}, in JoinLobby.`;
      this._router.navigate(ApplicationRoute.Error);
      return;
    }
    const initialLobbyState = event.data.initialLobbyState;
    const lobby = new Lobby(initialLobbyState.lobbyId, initialLobbyState.leaderId, initialLobbyState.gameActive);
    initialLobbyState.members.forEach(each => {
      lobby.addMember(new User(each.id, each.name));
    });
    this._lobbyService.lobby = lobby;
    this._router.navigate(ApplicationRoute.Lobby);
  }

  onCreate() {
    fetch(`${this._serverService.getHttpUrl()}/lobby/create`, { method: 'POST' })
      .then((res) => res.json())
      .then((resJson) => this.lobbyId = resJson.id);
  }

}
