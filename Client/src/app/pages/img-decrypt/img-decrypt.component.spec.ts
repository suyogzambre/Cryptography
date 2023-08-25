import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImgDecryptComponent } from './img-decrypt.component';

describe('ImgDecryptComponent', () => {
  let component: ImgDecryptComponent;
  let fixture: ComponentFixture<ImgDecryptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImgDecryptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImgDecryptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
