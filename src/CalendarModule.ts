import {NativeModules} from 'react-native';

interface CalendarModuleInterface {
  createCalendarEvent(name: string, location: string): void;
}

const {CalendarModule} = NativeModules;

export default CalendarModule as CalendarModuleInterface;
