import {HttpHeaders} from "@angular/common/http";

export const API = 'http://localhost:8080/api';
export const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:4200'})
};
