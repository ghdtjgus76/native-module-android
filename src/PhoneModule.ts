import {NativeModules} from 'react-native';

interface PhoneModuleInterface {
  makeCall(phoneNumber: string): Promise<string>;
}

const {PhoneModule: PhoneNativeModule} = NativeModules;

export const PhoneModule = PhoneNativeModule as PhoneModuleInterface;
