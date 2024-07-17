import {NativeEventEmitter, NativeModules} from 'react-native';

interface CalendarModuleInterface {
  createCalendarEvent(name: string, location: string): Promise<number>;
}

const {CalendarModule: CalendatNativeModule} = NativeModules;

export const CalendarEventEmitter = new NativeEventEmitter(
  CalendatNativeModule,
);

export const CalendarModule = CalendatNativeModule as CalendarModuleInterface;
