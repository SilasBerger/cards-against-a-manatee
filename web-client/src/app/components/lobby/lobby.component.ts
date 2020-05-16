import {Component, OnDestroy, OnInit} from '@angular/core';
import {LobbyService} from '../../services/lobby.service';
import Lobby from '../../model/lobby';
import {WebsocketConnectionService} from '../../services/websocket-connection.service';
import User from '../../model/user';
import {Subscription} from 'rxjs';
import BackendEvent from '../../model/backend-event';
import WhiteCard from '../../model/white-card';
import BlackCard from '../../model/black-card';
import {UserService} from '../../services/user.service';
import {ApplicationRoute, ApplicationRouter} from '../../routing/routing';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.scss']
})
export class LobbyComponent implements OnInit, OnDestroy {

  lobby: Lobby;
  private _wsSubscription: Subscription;

  personalWhiteCards: WhiteCard[] = [];
  placedWhiteCards: WhiteCard[] = [];
  currentBlackCard: BlackCard = null;
  gameActive = false;
  roundActive = false;
  turnActive = false;

  constructor(private _lobbyService: LobbyService,
              private _userService: UserService,
              private _router: ApplicationRouter,
              private _websocketConnectionService: WebsocketConnectionService) {
    if (!this._lobbyService.lobby) {
      this._router.navigate(ApplicationRoute.JoinLobby);
    }
    this.lobby = this._lobbyService.lobby;
    this._wsSubscription = this._websocketConnectionService.onEventSubject
      .subscribe((event) => this.handleEvent(event));
  }

  removeCard(card: WhiteCard, list: WhiteCard[]) {
    let index = -1;
    for (let i = 0; i < list.length; i++) {
      if (list[i].id === card.id) {
        index = i;
        break;
      }
    }
    if (index >= 0) {
      list.splice(index, 1);
    }
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this._wsSubscription.unsubscribe();
    this._lobbyService.lobby = null;
    this._websocketConnectionService.send('request.request_exit_lobby', {});
  }

  handleEvent(event: BackendEvent) {
    switch (event.headers.eventName) {
      case 'notification.notify_game_started': {
        this.gameActive = true;
        this.currentBlackCard = null;
        this.placedWhiteCards = [];
        this.personalWhiteCards = [];
        break;
      }

      case 'gameupdate.game_update_new_white_cards': {
        this.roundActive = true;
        this.currentBlackCard = null;
        this.placedWhiteCards = [];
        this.personalWhiteCards = [];
        event.data.cards.forEach(
          card => this.personalWhiteCards.push(new WhiteCard(card.id, card.text, null))
        );
        break;
      }

      case 'gameupdate.game_update_new_black_card': {
        this.turnActive = true;
        this.placedWhiteCards = [];
        const cardData = event.data.card;
        this.currentBlackCard = new BlackCard(cardData.id, cardData.text, cardData.pick, null);
        break;
      }

      case 'gameupdate.game_update_played_white_card': {
        const cardData = event.data.card;
        const card = new WhiteCard(cardData.id, cardData.text, event.data.playerId);
        // TODO: Only do this if the placed cards list doesn't already contain that card.
        this.placedWhiteCards.push(card);
        break;
      }

      case 'gameupdate.game_update_turn_over': {
        this.turnActive = false;
        break;
      }

      case 'gameupdate.game_update_game_over': {
        this.roundActive = false;
        this.turnActive = false;
        break;
      }

      // TODO: New leader.

      default: {
        console.log('Unhandled event.', event);
      }
    }
  }

  playWhiteCard(card: WhiteCard) {
    if (!this.turnActive) {
      return;
    }
    this.removeCard(card, this.personalWhiteCards);
    this._websocketConnectionService.send('move.move_play_white_card', {
      cardId: card.id
    });
  }

  requestNewGame() {
    console.log('Requesting start game.');
    this._websocketConnectionService.send('request.request_new_game', {
      gameModeName: '',
      ruleSet: {}
    });
  }

  requestStartRound() {
    console.log('Requesting start round.');
    this._websocketConnectionService.send('gamerequest.game_request_start_game', {});
  }

  requestStartTurn() {
    console.log('Requesting next turn.');
    this._websocketConnectionService.send('gamerequest.game_request_next_turn', {});
  }

  get isLeader() {
    return this._userService.userId === this.lobby.leaderId;
  }

  get members(): User[] {
    return this.lobby.members.sort((a, b) => {
      if (a.userName < b.userName) {
        return -1;
      } else if (a.userName > b.userName) {
        return 1;
      } else {
        return 0;
      }
    });
  }

}
