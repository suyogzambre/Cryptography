import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImgEncComponent } from './img-enc.component';

describe('ImgEncComponent', () => {
  let component: ImgEncComponent;
  let fixture: ComponentFixture<ImgEncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImgEncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImgEncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
