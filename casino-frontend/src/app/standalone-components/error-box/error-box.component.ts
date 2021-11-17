import {Component, OnDestroy, OnInit} from '@angular/core';
import {ErrorBoxService} from "./error-box.service";

@Component({
  selector: 'app-error-box',
  templateUrl: './error-box.component.html',
  styleUrls: ['./error-box.component.scss']
})
export class ErrorBoxComponent implements OnInit, OnDestroy {

  private readonly defaultMessage = "Error";
  errorMessage = "Error";
  errorVisible: boolean = false;

  constructor(private errorService: ErrorBoxService) { }

  ngOnInit(): void {
    this.errorService.eventEmitter.subscribe(message => this.showError(message));
  }

  ngOnDestroy(): void {
    this.errorService.eventEmitter.unsubscribe();
  }

  public showError(message: string) {
    this.errorMessage = message;
    this.errorVisible = true;
  }

  closeError() {
    this.errorVisible = false;
    this.errorMessage = this.defaultMessage;
  }
}
