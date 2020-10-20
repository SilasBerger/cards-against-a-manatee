import {Component, Input, OnInit} from '@angular/core';
import User from '../../model/user';

@Component({
  selector: 'app-lobby-members-list',
  templateUrl: './lobby-members-list.component.html',
  styleUrls: ['./lobby-members-list.component.scss']
})
export class LobbyMembersListComponent implements OnInit {

  @Input()
  members: User[];

  @Input()
  leaderId: string;

  constructor() { }

  ngOnInit(): void {
  }

}
