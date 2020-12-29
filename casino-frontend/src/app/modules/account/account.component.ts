import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {CasinoUserDto} from "../../model/data";
import {DataService} from "../../services/data.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
              private data: DataService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.data.getCasinoUser(this.tokenStorage.getUser().username)
      .then(response => this.casinoUser = response as CasinoUserDto);
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
    this.data.editCasinoUser({
      balance: null,
      birthDate: this.editForm.value.birthDate,
      country: this.editForm.value.country,
      firstName: this.editForm.value.firstName,
      nickname: this.casinoUser.nickname,
      secondName: this.editForm.value.secondName
    })
      .then(response => {
        this.casinoUser = response;
        this.editingInfo = false;
        this.showError = false;
      })
      .catch(() => this.showError = true);
  }
}
