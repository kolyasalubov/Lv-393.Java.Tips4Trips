import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
 
@Injectable()
export class ImageService {
 
  constructor(private http: HttpClient) {}
 
  deleteFiles(storageUrl: String) {
      return this.http.delete(storageUrl);
  }

  pushFileToStorage(file: File, storageUrl: String, requestType: String): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
 
    formdata.append('file', file);
 
    const req = new HttpRequest(requestType, storageUrl, formdata, {
      reportProgress: true,
      responseType: 'application/json'
    });
 
    return this.http.request(req);
  }

  pushFilesToStorage(files: File[], storageUrl: String,
                    requestType: String): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
 
    for(let file in files) {
      formdata.append('files', file);
    }
 
    const req = new HttpRequest(requestType, storageUrl, formdata, {
      reportProgress: true,
      responseType: 'application/json'
    });
 
    return this.http.request(req);
  }
}