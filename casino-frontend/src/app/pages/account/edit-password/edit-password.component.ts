import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {EditPasswordService} from "./edit-password.service";

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.scss']
})
export class EditPasswordComponent implements OnInit {

  passwordForm: FormGroup
  pageState: Observable<EditPasswordPageState>;

  constructor(private service: EditPasswordService) {}

  ngOnInit(): void {
    this.pageState = this.service.state;
    this.passwordForm = this.service.createForm();
  }

  changePassword(): void {
    this.service.changePassword(this.passwordForm);
  }
}

export type EditPasswordPageState = Readonly<{
  errorMessage: string;
  error: boolean;
  success: boolean;
  buttonClickable: boolean;
}>
