import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { ImageService } from '../../image.service';
import { ImageDetailsComponent } from '../image-details/image-details.component';

@Component({
  selector: 'app-image-upload-form',
  templateUrl: './image-upload-form.component.html',
  styleUrls: ['./image-upload-form.component.css']
})
export class ImageUploadFormComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  currentFilesUpload: File[];
  progress: { percentage: number } = { percentage: 0 };
  imageToDelete: string;
  xmlRequest: XMLHttpRequest;
  storageURL: string;
  @Input() set setStorageURL(val: string) {
    console.log(val);
    console.log(this.storageURL);
    this.storageURL = val;
  }
  @Input() requestType: string;
  @Input() multiple: Boolean = false;
  uploadedImages: string[] = null;
  @Input() set setUploadedImages(val: string[]) {
    console.log(val);
    console.log(this.uploadedImages);
    this.uploadedImages = val;
  }
  @Input() set UploadStart(val: boolean) {
    console.log(val);
    console.log(this.storageURL);
      if(val) {
        if (this.multiple) {
          this.uploadMultipleFiles();
        } else {
          this.upload();
        }
      }
  }
  @Output() uploaded: EventEmitter<boolean> = new EventEmitter();


  constructor(private uploadService: ImageService) { 
  }
 
  ngOnInit() {
  }
 
  delete(url: string) {
	this.imageToDelete=url;
    this.uploadService.deleteFiles(this.imageToDelete).subscribe(() => console.log("image deleted"));
	this.imageToDelete = undefined;
  }

  selectFile(event) {
    const file = event.target.files.item(0);
    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      alert('invalid format!');
    }
  }

  selectMultipleFiles(event) {
    for (var i = 0; i< event.target.files.length; i++) {
      let file = event.target.files.item(i);
      if (file.type.match('image.*')) {
        this.selectedFiles = event.target.files;
      } else {
        alert('invalid format!');
      }
    }
  }

  upload() {
    console.log(11111111111111111111);
    if (this.selectedFiles == null)
      return;
    this.progress.percentage = 0;
 
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload,
      this.storageURL, this.requestType)
    .subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        this.uploaded.emit(true);
      }
    });
 
    this.selectedFiles = undefined;
  }

  uploadMultipleFiles() {
    console.log(11111111111111111111);
    if (this.selectedFiles == null)
      return;
    this.progress.percentage = 0;
    this.fileListToArray();
	  console.log(this.currentFilesUpload);
    this.xmlRequest = this.uploadService.pushFilesToStorage(this.currentFilesUpload,
      this.storageURL, this.requestType);
      
      const setPercentage = this.setPercentage;
      this.xmlRequest.upload.onprogress = function(event) {
        if (event.lengthComputable) {
          setPercentage(Math.round(100 * event.loaded / event.total));
        }
      };

      this.xmlRequest.onload = function() {
        if (this.status === 200) {
          console.log('Files completely uploaded');
        }
      };
      const formdata = new FormData();
      for (let i = 0; i < this.currentFilesUpload.length; i++) {
        formdata.append('files', this.currentFilesUpload[i]);
        console.log(this.currentFilesUpload[i]);
      }
      this.xmlRequest.send(formdata);
    this.selectedFiles = undefined;
  }

  fileListToArray() {
    let files = [];
    for (let i = 0; i < this.selectedFiles.length; i++) {
      files.push(this.selectedFiles.item(i));
    }
    this.currentFilesUpload = files;
  }

  setPercentage = (percentage: number)  => {
    this.progress.percentage = percentage;
  }
}
