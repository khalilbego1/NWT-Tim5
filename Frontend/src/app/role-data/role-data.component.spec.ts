import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleDataComponent } from './role-data.component';

describe('RoleDataComponent', () => {
  let component: RoleDataComponent;
  let fixture: ComponentFixture<RoleDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoleDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoleDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
