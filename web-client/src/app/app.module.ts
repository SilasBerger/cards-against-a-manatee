import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainContainerComponent } from './components/main-container/main-container.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import {FormsModule} from '@angular/forms';
import {UserService} from './services/user.service';
import ServerService from './services/server.service';
import {RouterModule} from '@angular/router';
import {WebsocketConnectionService} from './services/websocket-connection.service';
import { JoinLobbyComponent } from './components/join-lobby/join-lobby.component';
import { ErrorComponent } from './components/error/error.component';
import {SharedStateService} from './services/shared-state.service';
import {LobbyService} from './services/lobby.service';
import { LobbyComponent } from './components/lobby/lobby.component';
import { LobbyMembersListComponent } from './components/lobby-members-list/lobby-members-list.component';
import { GameComponent } from './components/game/game.component';
import {ApplicationRoute} from './routing/routing';
import { PersonalCardsTrayComponent } from './components/personal-cards-tray/personal-cards-tray.component';

const root = [
  { path: ApplicationRoute.CreateUser.valueOf(), component: CreateUserComponent },
  { path: ApplicationRoute.JoinLobby.valueOf(), component: JoinLobbyComponent },
  { path: ApplicationRoute.Lobby.valueOf(), component: LobbyComponent },
  { path: ApplicationRoute.Error.valueOf(), component: ErrorComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MainContainerComponent,
    CreateUserComponent,
    JoinLobbyComponent,
    ErrorComponent,
    LobbyComponent,
    LobbyMembersListComponent,
    GameComponent,
    PersonalCardsTrayComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(root)
  ],
  providers: [
    UserService,
    ServerService,
    WebsocketConnectionService,
    SharedStateService,
    LobbyService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
