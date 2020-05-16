import {Injectable} from '@angular/core';
import Issue from './issue';

@Injectable({
  providedIn: 'root'
})
export class DebugContextServiceService {

  public readonly isDebug = false;
  public readonly preventNavigateOnIssue: Issue[] = [
    Issue.WebSocketDisconnected,
  ];

  constructor() { }
}
