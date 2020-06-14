import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-personal-cards-tray',
  templateUrl: './personal-cards-tray.component.html',
  styleUrls: ['./personal-cards-tray.component.scss']
})
export class PersonalCardsTrayComponent implements OnInit {

  isOpen: boolean;

  constructor() {
    this.isOpen = false;
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

}
