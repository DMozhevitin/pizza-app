export class Product {

  public id: number;

  public name: string;

  public uiName: string;

  public description: string;

  public prices: number[];

  public sizes: number[];

}

export class ProductWithQty {

  public product: Product;

  public size: number;

  public qty: number;

  public increase: boolean;
}

export class Cart {

  public userId: number;

  public cartItems: ProductWithQty[] = [];
}

export class Order {

  public user: object;

  public phoneNumber: string;

  public address: string;

  public name: string;

  public cart: Cart;

  public date: string;

}
