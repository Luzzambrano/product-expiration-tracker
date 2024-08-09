import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpService } from '../../@core/services/http.service';
import { Observable } from 'rxjs';
import { Product } from './product';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private endPoint = `${environment.gdcApiUrl}/products`;

  constructor(private httpService: HttpService) {}

  getProducts(): Observable<Product[]> {
    return this.httpService
      .get(this.endPoint)
      .pipe(
        map((products: Product[]) =>
          products.map((product: Product) => this.transformResponse(product)),
        ),
      );
  }

  getProductsExpiringThisMonth(): Observable<Product[]> {
    return this.httpService
      .get(`${this.endPoint}/expiring`)
      .pipe(
        map((products: Product[]) =>
          products.map((product: Product) => this.transformResponse(product)),
        ),
      );
  }

  private transformResponse(data: any): Product {
    return {
      id: data.id,
      name: data.name,
      sku: data.sku,
      brand: data.brand,
      description: data.description,
      cost: data.cost,
      price: data.price,
      category: data.category,
      weight: data.weight,
      quantity: data.quantity,
      stock: data.stock,
      supplier: data.supplier,
      expirationDate: data.expiration_date, // Map JSON expiration_date to expirationDate
    };
  }
}
