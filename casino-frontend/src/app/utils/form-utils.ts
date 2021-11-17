import {FormGroup} from "@angular/forms";

export function checkPasswordMatch(form: FormGroup): void {
  if(form.controls.password.value !== form.controls.matchingPassword.value) {
    form.controls.matchingPassword.setErrors({'invalid': true});
  } else {
    form.controls.matchingPassword.setErrors(null);
  }
}
