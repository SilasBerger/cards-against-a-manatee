import {Component, Input, OnInit} from '@angular/core';
import WhiteCard from '../../model/white-card';

@Component({
  selector: 'app-personal-cards-tray',
  templateUrl: './personal-cards-tray.component.html',
  styleUrls: ['./personal-cards-tray.component.scss']
})
export class PersonalCardsTrayComponent implements OnInit {

  @Input()
  isOpen: boolean;

  @Input()
  canPlay: boolean;

  // TODO: Make this generic to black and white cards.
  @Input()
  personalCards: WhiteCard[];

  @Input()
  cardPlayedCallback: (card: WhiteCard) => void;

  constructor() {
    this.isOpen = false;
    this.canPlay = true;
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

  onCardPlayed(card: WhiteCard) {
    this.cardPlayedCallback(card);
  }

}
