import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocEncComponent } from './doc-enc.component';

describe('DocEncComponent', () => {
  let component: DocEncComponent;
  let fixture: ComponentFixture<DocEncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocEncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocEncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
