import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {DebugContextServiceService} from '../services/debug-context-service.service';
import Issue from '../services/issue';

@Injectable({
  providedIn: 'root'
})
class ApplicationRouter {

  constructor(private _router: Router, private _debugContext: DebugContextServiceService) {
  }

  navigate(route: ApplicationRoute) {
    this._router.navigate([route.valueOf()]);
  }

  navigateOnIssue(issue: Issue) {
    if (this.shouldPreventNavigateOnIssue(issue)) {
      // Navigation on this issue is currently disabled for debugging - do nothing.
      return;
    }
    switch (issue) {
      case Issue.UserNotSet:
      case Issue.WebSocketDisconnected:
        this.navigate(ApplicationRoute.CreateUser);
        break;
      case Issue.NotInLobby:
        this.navigate(ApplicationRoute.JoinLobby);
        break;
      default:
        console.error('Trying to navigate to for unhandled issue: ' + issue);
    }
  }

  private shouldPreventNavigateOnIssue(issue: Issue): boolean {
    return this._debugContext.isDebug
      && this._debugContext.preventNavigateOnIssue
      && (this._debugContext.preventNavigateOnIssue.indexOf(issue) >= 0);
  }
}

enum ApplicationRoute {
  CreateUser = '',
  JoinLobby = 'joinlobby',
  Lobby = 'lobby',
  Error = 'error'
}

export { ApplicationRouter, ApplicationRoute };
