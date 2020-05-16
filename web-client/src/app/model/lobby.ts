import User from './user';

export default class Lobby {

  private _members: User[];

  constructor(private _id: string, private _leaderId: string, private _gameActive: boolean) {
    this._members = [];
  }

  get gameActive(): boolean {
    return this._gameActive;
  }

  set gameActive(value: boolean) {
    this._gameActive = value;
  }

  get members(): User[] {
    return this._members;
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get leaderId(): string {
    return this._leaderId;
  }

  set leaderId(value: string) {
    this._leaderId = value;
  }

  addMember(member: User) {
    this.members.push(member);
  }

  removeMember(memberId: string) {
    let index = -1;
    for (let i = 0; i < this._members.length; i++) {
      if (this._members[i].id === memberId) {
        index = i;
        break;
      }
    }
    if (index >= 0) {
      this._members.splice(index, 1);
    }
  }
}
