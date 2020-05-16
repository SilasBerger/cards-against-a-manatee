import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LobbyMembersListComponent } from './lobby-members-list.component';

describe('LobbyMembersListComponent', () => {
  let component: LobbyMembersListComponent;
  let fixture: ComponentFixture<LobbyMembersListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LobbyMembersListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LobbyMembersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
