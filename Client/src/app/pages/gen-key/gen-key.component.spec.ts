import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenKeyComponent } from './gen-key.component';

describe('GenKeyComponent', () => {
  let component: GenKeyComponent;
  let fixture: ComponentFixture<GenKeyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenKeyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenKeyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
