import { Component, OnInit } from '@angular/core';
import { ProductsService } from './products.service';
import { Product } from './product';

@Component({
  selector: 'ngx-products',
  templateUrl: './products.component.html',
  styles: [
    `
      /* styles.css or component-specific CSS file */
      .smart-table-container {
        width: 100%;
        overflow-x: auto;
      }
    `,
  ],
})
export class GdcProductsComponent implements OnInit {
  productsTableTile = 'Concentrated Product Inventory - Acapulco, NL';
  products: Product[] = [];

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
        type: 'string',
      },
      stock: {
        title: 'Stock',
        type: 'string',
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

  ngOnInit() {
    this.productService.getProducts().subscribe(
      (products: Product[]) => {
        this.products = products;
      },
      (error) => {
        this.products = [];
      },
    );
  }
}
