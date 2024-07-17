import {NativeModules} from 'react-native';

interface ImagePickerModuleInterface {
  pickImage(): Promise<string>;
}

const {ImagePickerModule: ImagePickerNativeModule} = NativeModules;

export const ImagePickerModule =
  ImagePickerNativeModule as ImagePickerModuleInterface;
