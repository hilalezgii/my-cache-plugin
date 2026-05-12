import { WebPlugin } from '@capacitor/core';
import type { ExamplePlugin } from './definitions';

export class ExampleWeb extends WebPlugin implements ExamplePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    return options;
  }
  async set(options: { key: string; value: string }): Promise<void> {
    localStorage.setItem(options.key, options.value);
  }
  async get(options: { key: string }): Promise<{ value: string | null }> {
    const value = localStorage.getItem(options.key);
    return { value };
  }
  async remove(options: { key: string }): Promise<void> {
    localStorage.removeItem(options.key);
  }
}
