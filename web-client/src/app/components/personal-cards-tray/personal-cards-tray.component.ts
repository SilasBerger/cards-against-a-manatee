import {Component, Input, OnInit} from '@angular/core';
import WhiteCard from '../../model/white-card';

@Component({
  selector: 'app-personal-cards-tray',
  templateUrl: './personal-cards-tray.component.html',
  styleUrls: ['./personal-cards-tray.component.scss']
})
export class PersonalCardsTrayComponent implements OnInit {

  isOpen: boolean;
  _canPlay: boolean;

  // TODO: Make this generic to black and white cards.
  @Input()
  personalCards: WhiteCard[];

  @Input()
  cardPlayedCallback: (card: WhiteCard) => void;

  constructor() {
    this.isOpen = false;
    this._canPlay = true;
  }

  ngOnInit(): void {
  }

  toggleTray() {
    this.isOpen = !this.isOpen;
  }

  showTray() {
    this.isOpen = true;
  }

  hideTray() {
    this.isOpen = false;
  }

  get canPlay() {
    return this._canPlay;
  }

  set canPlay(canPlay: boolean) {
    this._canPlay = canPlay;
  }

  onCardPlayed(card: WhiteCard) {
    this.cardPlayedCallback(card);
  }

}
