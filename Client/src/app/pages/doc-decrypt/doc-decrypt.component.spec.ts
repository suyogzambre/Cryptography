import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocDecryptComponent } from './doc-decrypt.component';

describe('DocDecryptComponent', () => {
  let component: DocDecryptComponent;
  let fixture: ComponentFixture<DocDecryptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocDecryptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocDecryptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
