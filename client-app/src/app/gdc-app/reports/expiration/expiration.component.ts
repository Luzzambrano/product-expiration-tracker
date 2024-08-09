import { Component, OnInit } from '@angular/core';
import { Product } from '../../products/product';
import { ProductsService } from '../../products/products.service';

@Component({
  selector: 'ngx-expiration',
  templateUrl: './expiration.component.html',
  styleUrls: ['./expiration.component.scss'],
})
export class GdcExpirationComponent implements OnInit {
  expirationTableTile = 'Expiration Report - Acapulco, NL';
  expiration: Product[] = [];

  settings = {
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      name: {
        title: 'Name',
        type: 'string',
      },
      sku: {
        title: 'SKU',
        type: 'string',
      },
      brand: {
        title: 'Brand',
        type: 'string',
      },
      description: {
        title: 'Description',
        type: 'string',
      },
      cost: {
        title: 'Cost',
        type: 'string',
      },
      price: {
        title: 'Price',
        type: 'string',
      },
      category: {
        title: 'Category',
        type: 'string',
      },
      weight: {
        title: 'Weight',
        type: 'string',
      },
      quantity: {
        title: 'Quantity',
        type: 'number',
      },
      stock: {
        title: 'Stock',
        type: 'number',
      },
      supplier: {
        title: 'Supplier',
        type: 'string',
      },
      expirationDate: {
        title: 'Expiration Date',
        type: 'string',
      },
    },
  };

  constructor(private productService: ProductsService) {}

  ngOnInit(): void {
    this.productService.getProductsExpiringThisMonth().subscribe(
      (products: Product[]) => {
        this.expiration = products;
      },
      (error) => {
        this.expiration = [];
      },
    );
  }

  onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }
}
