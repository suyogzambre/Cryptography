import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TextDecryptComponent } from './text-decrypt.component';

describe('TextDecryptComponent', () => {
  let component: TextDecryptComponent;
  let fixture: ComponentFixture<TextDecryptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TextDecryptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TextDecryptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
