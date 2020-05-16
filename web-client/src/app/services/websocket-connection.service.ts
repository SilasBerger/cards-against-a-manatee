import { Injectable } from '@angular/core';
import ServerService from './server.service';
import {PartialObserver, Subject} from 'rxjs';
import User from '../model/user';
import BackendEventHeaders from '../model/backend-event-headers';
import BackendEvent from '../model/backend-event';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import {LobbyService} from './lobby.service';
import {ApplicationRouter} from '../routing/routing';
import Issue from './issue';

@Injectable({
  providedIn: 'root'
})
export class WebsocketConnectionService {

  websocket: WebSocket;
  onOpenSubject: Subject<void>;
  onEventSubject: Subject<BackendEvent>;

  constructor(private _serverService: ServerService, private _router: ApplicationRouter) {
    this.onOpenSubject = new Subject<void>();
    this.onEventSubject = new Subject<BackendEvent>();
  }

  connect(user: User) {
    this.websocket = new WebSocket(`${this._serverService.getWsUrl()}/player/connect/${user.id}`);
    this.websocket.onopen = () => this.onOpenSubject.next();
    this.websocket.onmessage = (event) => {
      const eventObj = JSON.parse(event.data);
      const headers = new BackendEventHeaders(eventObj.headers.eventName, eventObj.headers.iat);
      this.onEventSubject.next(new BackendEvent(headers, eventObj.data));
    };
    this.websocket.onclose = () => this.onEventSubject.complete();
    this.onEventSubject.subscribe(() => {}, () => {}, () => {
      this._router.navigateOnIssue(Issue.WebSocketDisconnected);
    });
  }

  send(eventName: string, data: any) {
    if (!this.isConnected()) {
      return;
    }
    const headers = {
      eventName,
      iat: Date.now()
    };
    this.websocket.send(JSON.stringify({headers, data}));
  }

  isConnected(): boolean {
    if (!this.websocket) {
      return false;
    }
    return this.websocket.readyState === 1;
  }
}
