import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetilsComponent } from './user-detils.component';

describe('UserDetilsComponent', () => {
  let component: UserDetilsComponent;
  let fixture: ComponentFixture<UserDetilsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDetilsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
