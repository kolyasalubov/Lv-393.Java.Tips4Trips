import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
 
@Injectable({
  providedIn: 'root'
})
export class ImageService {
 
  constructor(private http: HttpClient) {}
 
  deleteFiles(storageUrl: string) {
      return this.http.delete(storageUrl);
  }

  pushFileToStorage(file: File, storageUrl: string, requestType: string): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
 
    formdata.append('file', file);
 
    const req = new HttpRequest(requestType, storageUrl, formdata, {
      reportProgress: true,
      responseType: 'json'
    });
 
    return this.http.request(req);
  }

  pushFilesToStorage(files: File[], storageUrl: string,
                    requestType: string): XMLHttpRequest {
    let xhr = new XMLHttpRequest();
    xhr.open(requestType, storageUrl, true);
    return xhr;
  }
}
