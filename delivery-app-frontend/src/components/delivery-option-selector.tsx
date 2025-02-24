import { DELIVERY_OPTIONS } from "@/utils/constants";
import Link from "next/link";



export default function DeliveryOptionSelector() {
    return (
        <div>
          <h2>Select develivery option</h2>
          {
            <div className="flex flex-row">
            {DELIVERY_OPTIONS.map(option => (
              <p key={option} className="mr-2"><Link className="flex h-full w-full select-none flex-col justify-end rounded-md bg-gradient-to-b from-muted/50 to-muted p-2 no-underline outline-none focus:shadow-md"
                   
              href={`/?deliveryOption=${option}`}>{option}</Link></p>
            ))}
            </div>
          }
        </div>
    )
}