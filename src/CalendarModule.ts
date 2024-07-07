import {NativeEventEmitter, NativeModules} from 'react-native';

interface CalendarModuleInterface {
  createCalendarEvent(name: string, location: string): Promise<number>;
}

const {CalendarModule} = NativeModules;
export const CalendarEventEmitter = new NativeEventEmitter(CalendarModule);

export default CalendarModule as CalendarModuleInterface;
