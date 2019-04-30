import { Component, OnInit } from '@angular/core';
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
  storageURL: string;
  requestType: string;
  multiple: Boolean;
  uploadedImages: ImageDetailsComponent[];
 
  constructor(private uploadService: ImageService) { 
  }
 
  ngOnInit(storageURL: string, multiple: Boolean, requestType: string, uploadedImages: string[]) {
    this.storageURL = storageURL;
    this.multiple = multiple;
    this.requestType = requestType;
    if (uploadedImages != null) {
      this.uploadedImages = new ImageDetailsComponent[uploadedImages.length];
      for(let i =0; i<uploadedImages.length; i++) {
        this.uploadedImages[i] = new ImageDetailsComponent();
        this.uploadedImages[i].ngOnInit(uploadedImages[i]);
      }
    } else {
      this.uploadedImages = null;
    }
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
    this.progress.percentage = 0;
 
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload,
      this.storageURL, this.requestType)
    .subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
 
    this.selectedFiles = undefined;
  }

  uploadMultipleFiles() {
    this.progress.percentage = 0;
    this.fileListToArray();
    this.uploadService.pushFilesToStorage(this.currentFilesUpload,
      this.storageURL, this.requestType)
    .subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('Files are completely uploaded!');
      }
    });
 
    this.selectedFiles = undefined;
  }

  fileListToArray() {
    let files = new File[this.selectedFiles.length];
    for (let i = 0; i < this.selectedFiles.length; i++) {
      files[i] = this.selectedFiles.item(i);
    }
    this.currentFilesUpload = files;
  }
}
