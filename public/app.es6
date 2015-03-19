import {Component, Template, bootstrap} from 'angular2/angular2';

@Component({
  selector: 'my-app'
})
@Template({
  inline: '<h1>Hello {{ name }}</h1>'
})

class MyAppComponent {
  constructor() {
    this.name = 'World';
  }
}

bootstrap(MyAppComponent);

