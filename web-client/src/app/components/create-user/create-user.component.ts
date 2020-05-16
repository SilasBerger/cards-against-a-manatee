import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {WebsocketConnectionService} from '../../services/websocket-connection.service';
import User from '../../model/user';
import {ApplicationRoute, ApplicationRouter} from '../../routing/routing';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  userName: string;

  constructor(private _userService: UserService,
              private _websocketConnectionService: WebsocketConnectionService,
              private _router: ApplicationRouter) {
    this._userService = _userService;
    this._websocketConnectionService = _websocketConnectionService;
    this._router = _router;
    this._userService.reset();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (!this.userName || this.userName.length < 1) {
      console.error('Invalid user name (0 characters).');
      return;
    }
    this._userService.createUser(this.userName).then((user) => this.connectWebsocket(user));
  }

  private connectWebsocket(user: User) {
    this._websocketConnectionService.connect(user);
    this._websocketConnectionService.onOpenSubject.subscribe(() => this._router.navigate(ApplicationRoute.JoinLobby));
  }

}
