import SlotSelector from "@/components/available-delivery-slots";
import DeliveryOptionSelector from "@/components/delivery-option-selector";
import Header from "@/components/header";
import { DeliverySlotInfo, fetchDeliverySlots } from "@/utils/api-utils";


type SearchParams = Promise<{ deliveryOption: string }>


export default async function Home({ searchParams }: { searchParams: SearchParams }) {
  const { deliveryOption } = await searchParams
  const deliverySlots: DeliverySlotInfo[] = await fetchDeliverySlots(deliveryOption);

  return (
    <div className="grid grid-rows-[20px_1fr_20px] min-h-screen p-8 pb-20 gap-16 sm:p-20">
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        <Header />

        <DeliveryOptionSelector />

        <SlotSelector deliveryOption={deliveryOption} deliverySlots={deliverySlots} />

      </main>

    </div>
  );
}
