import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import {TranslateService} from "@ngx-translate/core";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {

  constructor(public navCtrl: NavController, 
			  private translate: TranslateService, 
			  public storage: Storage) {
		
	  this.storage.get('user_language').then((val) => {
		console.log('Your priviosly selected language is', val);
		this.translate.use(val);
	  });
	  
  }
  
  langSelected(lang){
	 
	  
	  console.log(lang+" language selected");
	  this.translate.use(lang);
	  
	  // set a key/value
	  this.storage.set('user_language', lang);
		
	 
  }
 
  
   public changeLanguage(language)
  {
    this.translate.use(language);
  }

}
