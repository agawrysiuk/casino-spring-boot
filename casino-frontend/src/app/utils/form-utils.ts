import {FormGroup, ValidationErrors} from "@angular/forms";

export class CustomValidators {
  static matchPassword(form: FormGroup): ValidationErrors | null {

    const password = form.get("password").value;
    const confirm = form.get("matchingPassword").value;


    if (password != confirm) { return { 'invalid': true } }

    return null;
  }
}
