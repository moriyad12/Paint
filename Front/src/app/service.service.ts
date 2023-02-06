import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {HttpErrorResponse} from '@angular/common/http';
import { Component } from '@angular/core';
import { BoardComponent } from './board/board.component';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private paintUrl ='http://localhost:8080';
  constructor(private http: HttpClient) {}
  public create(type:string,json:string)
  {
    var jj="a" ;
    var re=this.http.get(this.paintUrl +"/"+ "create"+"/"+type+"/"+json,{responseType:'text'});
    re.subscribe((result : string) =>{
      jj=result
      console.log(result);
    }, (error:HttpErrorResponse) =>{
      alert(error.message);
    });
    return json ;
  }
  public update(id:string,json:string)
  {
    var jj="a" ;
    var re= this.http.get(this.paintUrl +"/"+ "update"+"/"+id+"/"+json,{responseType:'text'});
    re.subscribe((result : string) =>{
      jj=result
      console.log(result);
    }, (error:HttpErrorResponse) =>{
      alert(error.message);
    });
    return json ;
  }
  public delete(id:string)
  {
    this.http.get(this.paintUrl +"/"+ "delete"+"/"+id).subscribe();
  }
  undo()
  {
    var re= this.http.get(this.paintUrl +"/"+ "undo",{responseType:"text"});
    re.subscribe((result : string) =>{
      var jj='' ;
      jj=result
      console.log(jj);
      BoardComponent.undo(jj) ;
    }, (error:HttpErrorResponse) =>{
      alert(error.message);
    });
  }
  redo()
  {
    var re= this.http.get(this.paintUrl +"/"+ "redo",{responseType:"text"});
    re.subscribe((result : string) =>{
      var jj='' ;
      jj=result
      console.log(jj);
      BoardComponent.redo(jj) ;
    }, (error:HttpErrorResponse) =>{
      alert(error.message);
    });
  }
  clear()
  {
    this.http.get(this.paintUrl +"/"+ "clear").subscribe();
  }
  save(path:string){
    this.http.get(this.paintUrl +"/"+ "save/"+path).subscribe();
  }
  loadJson(path:string){
    var re= this.http.get(this.paintUrl +"/"+ "loadJson/"+path,{responseType:"text"});
    re.subscribe((result : string) =>{
      var jj=JSON.parse(result);
      BoardComponent.load(jj);
    }, (error:HttpErrorResponse) =>{
      alert(error.message);
    });
  }
  loadXml(path:string){
    var re= this.http.get(this.paintUrl +"/"+ "loadXML/"+path,{responseType:"text"});
    re.subscribe((result : string) =>{
      var jj=JSON.parse(result);
      BoardComponent.load(jj);
    }, (error:HttpErrorResponse) =>{
      alert(error.message);
    });
  }


}

