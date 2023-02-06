import { Component, OnInit } from '@angular/core';
import Konva from 'konva';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {
  s:string ="";
  jsonFile:string="";
  xmlFile:string="";
  width = 0;
  height = 0;
  static stage :any ;
  static layer: any;
  static deleteFlag=false;
  static fillColor='black';
  static service: ServiceService;
  static tr:any;
  static ctr:any;
  constructor(service: ServiceService){
    BoardComponent.service = service;
    BoardComponent.service.clear();
  }

  ngOnInit(): void {
      this.width = window.innerWidth;
      this.height = window.innerHeight;
      BoardComponent.stage = new Konva.Stage({
        container: 'container',
        width: this.width,
        height: this.height,
      });

      BoardComponent.ctr =new Konva.Transformer({
        keepRatio: true,
        enabledAnchors: [
          'top-left',
          'top-right',
          'bottom-left',
          'bottom-right',
        ],
      });
      BoardComponent.tr =new Konva.Transformer();

      BoardComponent.stage.on('click tap', function (e: { target: any; }) {
        if (e.target === BoardComponent.stage) {
          BoardComponent.tr.nodes([]);
          BoardComponent.ctr.nodes([]);
        }
      });
      BoardComponent.layer=new Konva.Layer();
      BoardComponent.layer.add(BoardComponent.tr),BoardComponent.layer.add(BoardComponent.ctr);
      BoardComponent.service.clear();
  }
  // ------------------------------------------------------------------------------------------------------------------------------
  save(){
     console.log(this.s);
    var op="";
    for(var i=0;i<this.s.length;i++){
      if(this.s[i]=='/'||this.s[i]=='\\')op+='*';
      else op+=this.s[i];
    }
    console.log(op);
    BoardComponent.service.save(op);
  }
  loadX(){
    this.clear();
    console.log(this.xmlFile);
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var op="";
    for(var i=0;i<this.xmlFile.length;i++){
      if(this.xmlFile[i]=='/'||this.xmlFile[i]=='\\')op+='*';
      else op+=this.xmlFile[i];
    }
    BoardComponent.service.loadXml(op);
  }
  loadJ(){
    this.clear();
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var op="";
    for(var i=0;i<this.jsonFile.length;i++){
      if(this.jsonFile[i]=='/'||this.jsonFile[i]=='\\')op+='*';
      else op+=this.jsonFile[i];
    }
    BoardComponent.service.loadJson(op);
  }
  public static load(jj:string[]){
    for(var i=0;i<jj.length;i++){

      var circle=Konva.Node.create(jj[i]);
      BoardComponent.givetransformer(circle)
    }
  }
// --------------------------------------------------------------------------------------------------------------------------------------------
  und(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    BoardComponent.service.undo();
  }
  public static undo(json:string){
    if(json=="NON")return;
    console.log(json);
    var id='';
    for(var i=0;i<10;i++){
      id+=json[i];
    }
     let op='' as string;
    for(var i=10;i<json.length;i++){
      op+=json[i];
    }
    console.log(op);
    if(op=='delete'){
      var currentShape=BoardComponent.stage.find("#"+id)[0];
      if(currentShape!=null)
        currentShape.destroy();
    }else{
      var currentShape=BoardComponent.stage.findOne("#"+id);
      if(currentShape!=null)
        currentShape.destroy();
      var circle=Konva.Node.create(op);
      BoardComponent.givetransformer(circle)
    }
  }
  red(){
    console.log("a");
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    BoardComponent.service.redo();
  }
  public static redo(json:string){
    if(json=="NON")return;
    console.log(json);
    var id='';
    for(var i=0;i<10;i++){
      id+=json[i];
    }
    let op='' as string;
    for(var i=10;i<json.length;i++){
      op+=json[i];
    }
    console.log(op);
    var currentShape=BoardComponent.stage.findOne("#"+id);
      if(currentShape!=null)
      currentShape.destroy();
      var circle=Konva.Node.create(op);
      BoardComponent.givetransformer(circle)
  }
  clear(){
    this.width = window.innerWidth;
    this.height = window.innerHeight;
    BoardComponent.stage = new Konva.Stage({
      container: 'container',
      width: this.width,
      height: this.height,
    });
    BoardComponent.ctr =new Konva.Transformer({
      keepRatio: true,
      enabledAnchors: [
        'top-left',
        'top-right',
        'bottom-left',
        'bottom-right',
      ],
    });
    BoardComponent.tr =new Konva.Transformer();

    BoardComponent.stage.on('click tap', function (e: { target: any; }) {
      if (e.target === BoardComponent.stage) {
        BoardComponent.tr.nodes([]);
        BoardComponent.ctr.nodes([]);
      }
    });
    BoardComponent.layer=new Konva.Layer();
    BoardComponent.layer.add(BoardComponent.tr),BoardComponent.layer.add(BoardComponent.ctr);
    BoardComponent.service.clear();
  }
// --------------------------------------------------------------------------------------------------------------------------------------

  static checkColor(){
  var color = document.getElementById('colorPicker')as HTMLInputElement  ;
  BoardComponent.fillColor=color.value;
  }
  static givetransformer(shape: any){
    shape.on('mouseover', function () {
      document.body.style.cursor = 'pointer';
    });
    shape.on('mouseout', function () {
      document.body.style.cursor = 'default';
    });
    shape.on('transformend', function () {
      BoardComponent.service.update(shape.id(),BoardComponent.convert(shape.toJSON()));
    });
    shape.on('dragend',function(){ console.log(shape.toJSON());

      BoardComponent.service.update(shape.id(),BoardComponent.convert(shape.toJSON()));
    });
    shape.on("dblclick",function (){

        BoardComponent.checkColor();

        if(shape.fill()!=BoardComponent.fillColor){
          shape.fill(BoardComponent.fillColor);
          BoardComponent.service.update(shape.id(),BoardComponent.convert(shape.toJSON()));

      }
      console.log(BoardComponent.convert(shape.toJSON()));
    });
    shape.on("click",function(){
      if(BoardComponent.deleteFlag){
        BoardComponent.service.delete(shape.id());
        shape.destroy();
        BoardComponent.deleteFlag=false;
      }else {
        BoardComponent.ctr.nodes([shape]);
      }
    });
    BoardComponent.layer.add(shape);
    BoardComponent.stage.add(BoardComponent.layer);
  }

  static giveFlexableTransformer(shape : any){
    shape.on('mouseover', function () {
      document.body.style.cursor = 'pointer';
    });
    shape.on('dragend',function(){
      BoardComponent.service.update(shape.id(),BoardComponent.convert(shape.toJSON()));
    });
    shape.on('transformend', function () {
      BoardComponent.service.update(shape.id(),BoardComponent.convert(shape.toJSON()));
    });
    shape.on('mouseout', function () {
      document.body.style.cursor = 'default';
    });
    shape.on("dblclick",function (){
      if(BoardComponent.deleteFlag){
        BoardComponent.service.delete(shape.id());
        shape.destroy();
        BoardComponent.deleteFlag=false;
      }else {
        BoardComponent.checkColor();
        if(shape.fill()!=BoardComponent.fillColor){
          shape.fill(BoardComponent.fillColor);
          BoardComponent.service.update(shape.id(),BoardComponent.convert(shape.toJSON()));
        }
      }
      console.log(BoardComponent.convert(shape.toJSON()));
    });
    shape.on("click",function(){
      BoardComponent.tr.nodes([shape]);
    });
    BoardComponent.layer.add(shape);
    BoardComponent.stage.add(BoardComponent.layer);
  }
  circle(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var circle = new Konva.Circle({
      x: BoardComponent.stage.width() / 2,
      y: BoardComponent.stage.height() / 2,
      radius: 70,
      fill: '#FAF9F6',
      stroke: 'black',
      strokeWidth: 4,
      draggable: true,
      id:BoardComponent.makeid(),
    });
    console.log(circle);
    BoardComponent.service.create("circle",BoardComponent.convert(circle.toJSON() ) );
    BoardComponent.givetransformer(circle);

  }
  rect(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var box = new Konva.Rect({
      x: BoardComponent.stage.width() / 2,
      y: BoardComponent.stage.height() / 2,
      width: 500,
      height: 200,
      fill: '#FAF9F6',
      stroke: 'black',
      strokeWidth: 4,
      draggable: true,
      id:BoardComponent.makeid(),
    });
    BoardComponent.service.create("rectangle",BoardComponent.convert(box.toJSON()));
    BoardComponent.giveFlexableTransformer(box)
  }
  elipse(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);    var oval = new Konva.Ellipse({
      x: BoardComponent.stage.width() / 2,
      y: BoardComponent.stage.height() / 2,
      radiusX: 100,
      radiusY: 50,
      fill: '#FAF9F6',
      stroke: 'black',
      strokeWidth: 4,
      draggable: true,
      id:BoardComponent.makeid(),
    });
    BoardComponent.service.create("elipse",BoardComponent.convert(oval.toJSON()));
    BoardComponent.giveFlexableTransformer(oval)
  }

  line(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var box = new Konva.Rect({
      x: BoardComponent.stage.width() / 2,
      y: BoardComponent.stage.height() / 2-100,
      width: 1000,
      height: 0,
      scaleX:0.5,
      scaleY:0.5,
      fill: '#000000',
      stroke: 'black',
      strokeWidth: 4,
      draggable: true,
      id:BoardComponent.makeid(),
    });
    BoardComponent.service.create("square",BoardComponent.convert(box.toJSON()));
    BoardComponent.givetransformer(box)
  }
  tri(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var poly = new Konva.RegularPolygon({
      x: BoardComponent.stage.width() / 2,
      y: BoardComponent.stage.height() / 2,
      sides: 3,
      radius: 70,
      fill: '#FAF9F6',
      stroke: 'black',
      strokeWidth: 4,
      draggable: true,
      id:BoardComponent.makeid(),
    });
    BoardComponent.service.create("triangle",BoardComponent.convert(poly.toJSON()));
    BoardComponent.givetransformer(poly)
  }
  square(){
    BoardComponent.deleteFlag=false;
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    var box = new Konva.Rect({
      x: BoardComponent.stage.width() / 2,
      y: BoardComponent.stage.height() / 2,
      width: 100,
      height: 100,
      fill: '#FAF9F6',
      stroke: 'black',
      strokeWidth: 4,
      draggable: true,
      id:BoardComponent.makeid(),
    });
    BoardComponent.service.create("square",BoardComponent.convert(box.toJSON()));
    BoardComponent.givetransformer(box)
  }
  // ------------------------------------------------------------------------------------------------------------------
  delete(){
    BoardComponent.tr.nodes([]);
    BoardComponent.ctr.nodes([]);
    BoardComponent.deleteFlag=true;
  }
  static convert(json :string) :string{
      var str="";
      var number=0;
      for(var i=0;i<json.length;i++){
        if(json[i]=='{')number++;
        if (number>1){
          if(json[i]=='#')continue;
          str+=json[i];
        }
        if(json[i]=='}')break;
      }
      return str;
  }
  static  makeid() {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < 10; i++ ) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}
}
