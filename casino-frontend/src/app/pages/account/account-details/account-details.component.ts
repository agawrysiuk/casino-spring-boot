import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {CasinoUserDto} from "../../../model/data";
import {FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {AccountDetailsService} from "./account-details.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.scss']
})
export class AccountDetailsComponent implements OnInit {

  editForm: FormGroup;
  pageState: Observable<AccountDetailsPageState>;

  constructor(public tokenStorage: TokenStorageService,
              private service: AccountDetailsService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.pageState = this.service.getState(data.account);
    });
  }

  createEditForm(): void {
    this.editForm = this.service.createEditForm();
  }

  saveEditForm(): void {
    this.service.saveEditForm(this.editForm);
  }
}

export type AccountDetailsPageState = Readonly<{
  casinoUser: CasinoUserDto;
  editingInfo: boolean;
  showError: boolean;
}>
