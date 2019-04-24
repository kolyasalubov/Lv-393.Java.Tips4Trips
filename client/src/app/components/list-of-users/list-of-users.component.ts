import {Component, OnInit} from '@angular/core';
import {LittlepostModel} from "../../model/littlepost.model";
import {PagelittlepostModel} from "../../model/pagelittlepost.model";
import {AccountRoleModel} from "../../model/account-role.model";
import {PageAccountRoleModel} from "../../model/pageAccountRole.model";
import {BlogService} from "../blog/blog/blog.service";
import {ActivatedRoute} from "@angular/router";
import {ListUsersService} from "./listUsers.service";
import {AccountService} from "../authentication/account/account.service";
import {Account} from "../../model/account.model";

@Component({
  selector: 'app-list-of-users',
  templateUrl: './list-of-users.component.html',
  styleUrls: ['./list-of-users.component.css']
})
export class ListOfUsersComponent implements OnInit {

  id: number;
  max: number;
  num: number;
  postP: AccountRoleModel[] = null;
  pagePost: PageAccountRoleModel = null;

  accForUpdate: Account;

  constructor(private listServ: ListUsersService, private activatedRoute: ActivatedRoute, private accountService: AccountService) {
  }

  getPageClient(page: number): void {
    this.listServ.getPosts(page)
      .subscribe(data => {
        this.pagePost = data;
        this.max = this.pagePost.totalPages;
        this.num = this.pagePost.number;
        this.postP = this.pagePost.content;
      });
  }

  ngOnInit() {
    this.accForUpdate = new Account(null,
      null,
      null,
      null,
      null,
      null,
      null,
      null);
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    if (isNaN(this.id) || this.id < 1) {
      this.id = 1;
    }
    this.getPageClient(this.id);
  }

  update() {
    if (this.findUser()) {
      return;
    }
    this.accountService.updateAccountRole(this.accForUpdate).subscribe(data => {
      this.accForUpdate = data;
      alert(this.accForUpdate.email + ' is update');
    });
  }

  delete() {
    if (this.findUser()) {
      return;
    }
    // this.accountService.deleteAccount(this.accForUpdate.id).subscribe(data => {
    //   alert(this.accForUpdate.email + ' is delete');
    // });
  }

  findUser(): boolean {
    if (this.accForUpdate.email == null) {
      return true;
    }
    for (let i = 0; i < this.postP.length; i++) {
      if (this.postP[i].email == this.accForUpdate.email) {
        this.accForUpdate.id = this.postP[i].id;
        this.postP[i].role = this.accForUpdate.role;
        return false;
      }
    }
  }

}
