'use client';
import { bookDeliveryASAP, bookDeliveryToday, DeliverySlotInfo } from "@/utils/api-utils";
import { bookDeliverySlot } from "@/utils/api-utils";
import { Button } from "./ui/button";

const confirm = () => {
    alert(`Slot booked`);
    window.location.reload();
}

const cancel = (error: Error) => {
    alert(error)
}

export default function SlotSelector({ deliveryOption, deliverySlots }: { deliveryOption: string, deliverySlots: DeliverySlotInfo[] }) {

    console.log(deliveryOption, deliverySlots);
    
    if (deliveryOption === undefined)
        return <div>Please select a delivery option</div>;

    return (
        <div>
            <h2 className="mt-4 text-lg font-bold">Available Slots</h2>
            {
                deliveryOption !== "DRIVE" && (
                    <div>
                        <Button variant={"outline"} onClick={() => bookDeliveryASAP()
                            .then(() => confirm())
                            .catch((error: Error) => cancel(error))
                        }>Select ASAP</Button>
                        <Button variant={"outline"} onClick={() => bookDeliveryToday()
                            .then(() => confirm())
                            .catch((error: Error) => cancel(error))
                        }>Select Today</Button>
                    </div>
                )
            }
            <ul className="mt-4">
                {deliverySlots.map(slot => (
                    <li key={slot.id} className="mb-1"> <Button onClick={() => bookDeliverySlot(slot.id)
                        .then(() => confirm())
                        .catch((error: Error) => cancel(error))
                    }> Select Slot ={slot.id}  Date: {slot.slotStart}</Button> </li>
                ))}


            </ul>
        </div>
    );
}