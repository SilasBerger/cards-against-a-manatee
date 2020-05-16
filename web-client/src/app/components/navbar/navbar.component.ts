import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {LobbyService} from '../../services/lobby.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private _userService: UserService, private _lobbyService: LobbyService) { }

  ngOnInit(): void {
  }

  get userName() {
    return this._userService.userName;
  }

  get lobbyId() {
    return this._lobbyService.lobbyId;
  }

}
