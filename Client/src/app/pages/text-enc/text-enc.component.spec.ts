import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TextEncComponent } from './text-enc.component';

describe('TextEncComponent', () => {
  let component: TextEncComponent;
  let fixture: ComponentFixture<TextEncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TextEncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TextEncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
