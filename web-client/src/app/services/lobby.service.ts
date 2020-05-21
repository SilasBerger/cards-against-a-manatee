import {Injectable} from '@angular/core';
import User from '../model/user';
import Lobby from '../model/lobby';
import {Observer} from 'rxjs';
import BackendEvent from '../model/backend-event';
import {WebsocketConnectionService} from './websocket-connection.service';
import {ApplicationRoute, ApplicationRouter} from '../routing/routing';
import Issue from './issue';
import {isLineBreak} from 'codelyzer/angular/sourceMappingVisitor';

@Injectable({
  providedIn: 'root'
})
export class LobbyService {

  private _lobby: Lobby;
  private _webSocketSubscription: Observer<BackendEvent>;

  constructor(private _router: ApplicationRouter, websocketConnectionService: WebsocketConnectionService) {
    if (!websocketConnectionService.isConnected()) {
      this._router.navigateOnIssue(Issue.WebSocketDisconnected);
    }
    websocketConnectionService
      .onEventSubject
      .subscribe((event) => this.onEvent(event));
  }

  private onEvent(event: BackendEvent) {
    switch (event.headers.eventName) {
      case 'notification.notify_player_joined_lobby': {
        const userData = event.data.player;
        this.lobby.addMember(new User(userData.id, userData.name));
        break;
      }
      case 'notification.notify_player_left_lobby': {
        this.lobby.removeMember(event.data.playerId);
        break;
      }
      case 'notification.notify_new_leader': {
        this.lobby.leaderId = event.data.leaderId;
        break;
      }
    }
  }

  get lobby(): Lobby {
    return this._lobby;
  }

  set lobby(value: Lobby) {
    this._lobby = value;
  }

  get lobbyId() {
    if (!this._lobby) {
      return null;
    }
    return this._lobby.id;
  }

  reset() {
    this._lobby = null;
  }
}
