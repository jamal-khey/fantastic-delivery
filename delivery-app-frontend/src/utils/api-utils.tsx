import { API_BASE_URL } from "./constants";



export interface DeliverySlotInfo {
    id: number;
    deliveryOption: string;
    slotStart: string;
    slotEnd: string;
    orderUuid: string | null;
    _links: {
        self: { href: string };
    };
}

export async function fetchDeliverySlots(deliveryOption: string | undefined): Promise<DeliverySlotInfo[]> {
    if(deliveryOption === undefined)
      return [];

    try {
        const results = await (await fetch(`${API_BASE_URL}/data-api/deliverySlotEntities/search/findByDeliveryOptionAndOrderUuidIsNull?deliveryOption=${deliveryOption}`)).json();
        const slots = results._embedded.deliverySlotEntities;
        return slots.map((slot: DeliverySlotInfo) => ({
            ...slot,
            //@ts-expect-error 2345
            id: parseInt(slot._links.self.href.split('/').pop())
        }));
    } catch (error) {
        console.error(error);
    }
    return [];
}


export async function bookDeliverySlot(slotId: number) {
    
    const orderUuid = crypto.randomUUID();
    return await fetch(`${API_BASE_URL}/data-api/deliverySlotEntities/${slotId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ orderUuid })
        });
    
}

export async function bookDeliveryASAP() {
    const orderUuid = crypto.randomUUID();
    return await fetch(`${API_BASE_URL}/api/v1/delivery_asap/${orderUuid}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
}

export async function bookDeliveryToday() {
    const orderUuid = crypto.randomUUID();
    return await fetch(`${API_BASE_URL}/api/v1/delivery_today/${orderUuid}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
}
