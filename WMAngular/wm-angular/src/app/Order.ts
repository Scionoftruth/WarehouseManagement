export interface Order {
    orderId: number,
    transactionId: number,
    itemId: number,
    orderQty: number,
    itemName: string,
    itemPrice: number,
    invQuantity: number,
    status: string,
    customerName: string,
    employeeName: string,
    customerAddress: string
}