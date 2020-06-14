import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalCardsTrayComponent } from './personal-cards-tray.component';

describe('PersonalCardsTrayComponent', () => {
  let component: PersonalCardsTrayComponent;
  let fixture: ComponentFixture<PersonalCardsTrayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonalCardsTrayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalCardsTrayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
