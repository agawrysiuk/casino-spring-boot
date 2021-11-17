import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {CasinoUserDto} from "../../../model/data";
import {ConnectionService} from "../../../services/connection/connection.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DataService} from "../../../services/data.service";
import {AccountResolver} from "./account-resolver";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  casinoUser: CasinoUserDto;
  editForm: FormGroup;
  editingInfo: boolean = false;
  showError: boolean = false;

  constructor(private tokenStorage: TokenStorageService,
              private connection: ConnectionService,
              private fb: FormBuilder,
              private data: DataService) {
  }

  ngOnInit(): void {
    this.casinoUser = this.data.casinoUser;
  }

  getNickname() {
    return this.tokenStorage.getUser().username;
  }

  createEditForm() {
    this.editForm = this.fb.group({
      firstName: [this.casinoUser!.firstName, Validators.required],
      secondName: [this.casinoUser!.secondName, Validators.required],
      birthDate: [this.casinoUser!.birthDate, Validators.required],
      country: [this.casinoUser!.country, Validators.required]
    });
    this.editingInfo = true;
  }

  saveEditForm() {
    this.data.setCasinoUser({
      firstName: this.editForm.value.firstName,
      secondName: this.editForm.value.secondName,
      birthDate: this.editForm.value.birthDate,
      country: this.editForm.value.country
    })
      .subscribe(
        response => {
          this.casinoUser = this.data.casinoUser;
          this.editingInfo = false;
          this.showError = false;
        },
        catchError => this.showError = true
      );
  }

  isDepositDisabled() {
    return !this.casinoUser?.firstName && !this.casinoUser?.secondName;
  }
}
